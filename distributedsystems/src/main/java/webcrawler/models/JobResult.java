package webcrawler.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 27/11/2025
 */
@Data
public class JobResult {
    private String jobId;
    private List<PageInfo> pages;
    private int currentPage;
    private int pageSize;
    private long totalPages;
    private long totalUrls;

    public JobResult addPageInfo(PageInfo pageInfo) {
        if (pages == null) {
            pages = new ArrayList<>();
        }

        pages.add(pageInfo);
        return this;
    }
}
