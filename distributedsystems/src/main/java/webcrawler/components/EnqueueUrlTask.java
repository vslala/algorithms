package webcrawler.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import webcrawler.models.JobStatus;
import webcrawler.models.JobUrlMessage;
import webcrawler.repositories.JobUrlRepository;
import webcrawler.repositories.entities.JobUrl;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 28/11/2025
 */
@Order(4)
@Component
@Log4j2
public class EnqueueUrlTask implements Task{

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final JobUrlRepository jobUrlRepository;

    @Autowired
    public EnqueueUrlTask(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper, JobUrlRepository jobUrlRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.jobUrlRepository = jobUrlRepository;
    }

    @Override
    @SneakyThrows
    public TaskContext execute(TaskContext taskContext) {
        JobUrl jobUrl = taskContext.getJobUrl();
        Document document = taskContext.getDocument();
        log.info("Parsing HTML to extract links from: {}", jobUrl.getUrl());
        Map<JobUrl.UrlType, Set<String>> extractedUrlsByType = extractUrlsAndImages(document, jobUrl.getUrl());
        Set<String> extractedUrls = extractedUrlsByType.get(JobUrl.UrlType.URL);
        Set<String> extractedImages = extractedUrlsByType.get(JobUrl.UrlType.IMAGE);
        log.info("Extracted {} unique URLs and {} images from: {}",
                extractedUrls.size(), extractedImages.size(), jobUrl.getUrl());

        List<JobUrl> newJobUrls = new ArrayList<>();
        int nextDepth = jobUrl.getDepth() + 1;
        Long parentUrlId = jobUrl.getId();

        for (String url : extractedUrls) {
            JobUrl newJobUrl = new JobUrl();
            newJobUrl.setUrl(url);
            newJobUrl.setUrlType(JobUrl.UrlType.URL);
            newJobUrl.setJob(jobUrl.getJob());
            newJobUrl.setStatus(JobStatus.PENDING);
            newJobUrl.setDepth(nextDepth);
            newJobUrl.setParentUrlId(parentUrlId);
            newJobUrl.setCreatedAt(LocalDateTime.now());
            newJobUrl.setUpdatedAt(LocalDateTime.now());
            newJobUrls.add(newJobUrl);
        }

        for (String imageUrl : extractedImages) {
            JobUrl newJobUrl = new JobUrl();
            newJobUrl.setUrl(imageUrl);
            newJobUrl.setUrlType(JobUrl.UrlType.IMAGE);
            newJobUrl.setJob(jobUrl.getJob());
            newJobUrl.setStatus(JobStatus.COMPLETED);
            newJobUrl.setDepth(nextDepth);
            newJobUrl.setParentUrlId(parentUrlId);
            newJobUrl.setCreatedAt(LocalDateTime.now());
            newJobUrl.setUpdatedAt(LocalDateTime.now());
            newJobUrls.add(newJobUrl);
        }

        int pushedCount = 0;
        List<JobUrl> savedJobUrls = jobUrlRepository.saveAll(newJobUrls);
        for (JobUrl savedJobUrl : savedJobUrls) {
            if (savedJobUrl.getUrlType() == JobUrl.UrlType.URL) {
                JobUrlMessage message = new JobUrlMessage(
                    savedJobUrl.getId(),
                    savedJobUrl.getUrl(),
                    savedJobUrl.getUrlType().name(),
                    savedJobUrl.getJob().getJobId(),
                    savedJobUrl.getStatus().name(),
                    savedJobUrl.getDepth(),
                    savedJobUrl.getParentUrlId()
                );
                this.kafkaTemplate.send("url_frontier", this.objectMapper.writeValueAsString(message));
                pushedCount++;
            }
        }
        log.info("Pushed {} URLs to url_frontier topic (excluded {} images)",
                pushedCount, extractedImages.size());

        taskContext.setFinalStatus(JobStatus.COMPLETED);
        taskContext.setTerminate(true);
        return taskContext;
    }

    /**
     * Extract all URLs and images from the HTML document
     * Returns a map with URL type as key and set of URLs as value
     */
    private Map<JobUrl.UrlType, Set<String>> extractUrlsAndImages(Document document, String baseUrl) {
        Map<JobUrl.UrlType, Set<String>> result = new HashMap<>();
        result.put(JobUrl.UrlType.URL, new HashSet<>());
        result.put(JobUrl.UrlType.IMAGE, new HashSet<>());

        // Extract regular URLs from anchor tags
        Elements links = document.select("a[href]");
        for (Element link : links) {
            try {
                String href = link.attr("abs:href"); // Get absolute URL
                if (!href.isEmpty() && (href.startsWith("http://") || href.startsWith("https://"))) {
                    // Normalize URL by removing fragments
                    URI uri = new URI(href);
                    String normalizedUrl = new URI(
                            uri.getScheme(),
                            uri.getAuthority(),
                            uri.getPath(),
                            uri.getQuery(),
                            null // Remove fragment
                    ).toString();

                    // Check if this is an image URL based on extension
                    if (isImageUrl(normalizedUrl)) {
                        result.get(JobUrl.UrlType.IMAGE).add(normalizedUrl);
                    } else {
                        result.get(JobUrl.UrlType.URL).add(normalizedUrl);
                    }
                }
            } catch (Exception e) {
                log.debug("Failed to parse URL: {}", link.attr("href"), e);
            }
        }

        // Extract image URLs from img tags
        Elements images = document.select("img[src]");
        for (Element img : images) {
            try {
                String src = img.attr("abs:src"); // Get absolute URL
                if (!src.isEmpty() && (src.startsWith("http://") || src.startsWith("https://"))) {
                    URI uri = new URI(src);
                    String normalizedUrl = new URI(
                            uri.getScheme(),
                            uri.getAuthority(),
                            uri.getPath(),
                            uri.getQuery(),
                            null // Remove fragment
                    ).toString();
                    result.get(JobUrl.UrlType.IMAGE).add(normalizedUrl);
                }
            } catch (Exception e) {
                log.debug("Failed to parse image URL: {}", img.attr("src"), e);
            }
        }

        return result;
    }

    /**
     * Check if a URL points to an image based on file extension
     */
    private boolean isImageUrl(String url) {
        String lowerUrl = url.toLowerCase();
        return lowerUrl.endsWith(".jpg") || lowerUrl.endsWith(".jpeg") ||
                lowerUrl.endsWith(".png") || lowerUrl.endsWith(".gif") ||
                lowerUrl.endsWith(".bmp") || lowerUrl.endsWith(".webp") ||
                lowerUrl.endsWith(".svg") || lowerUrl.endsWith(".ico");
    }
}
