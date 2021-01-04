package com.bma.problemsolving.codewars.qualified;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Shopping {

    static class Category {
        private String name;
        private List<String> products;
        private int purchaseCount;

        public Category(String name, List<String> products) {
            this.name = name;
            this.products = products;
        }

        public List<String> getProducts() {
            return products;
        }

        public int getPurchaseCount() {
            return purchaseCount;
        }

        public void increaseCountIfPresent(List<String> purchase) {
            var count = new AtomicInteger(0);
            purchase.forEach(p -> {
                products.forEach(product -> {
                    if (product.equalsIgnoreCase(p))
                        count.getAndIncrement();
                });
            });

            this.purchaseCount += count.get();
        }

        public String getName() {
            return name;
        }
    }

    private List<Category> categories;

    public Shopping() {
        categories = new ArrayList<>();
        categories.add(new Category("Clothing", List.of("shirt", "sweater", "jacket", "shorts", "shoes")));
        categories.add(new Category("Sports", List.of("socks", "shoes", "shorts", "football", "helmet", "bag")));
        categories.add(new Category("Accessories", List.of("bag", "belt", "hat", "sunglasses", "watch")));
        categories.add(new Category("Electronics", List.of("TV", "camera", "headphones", "laptop")));
    }

    public List<String> popularCategories(Map<String, List<String>> usersPurchases) {
        if (Objects.isNull(usersPurchases)) throw new IllegalArgumentException();
        if (usersPurchases.isEmpty())
            return Collections.emptyList();

        verifyAllPurchases(usersPurchases);
        categorizePurchases(usersPurchases);

        return findMostLovedCategories();
    }

    private List<String> findMostLovedCategories() {
        if (categories.stream().mapToInt(Category::getPurchaseCount).sum() == 0)
            return Collections.emptyList();

        List<Category> mostLovedCategories = categories
                .stream()
                .max(Comparator.comparingInt(Category::getPurchaseCount))
                .map(mlc -> categories.stream()
                        .filter(category -> category.getPurchaseCount() == mlc.getPurchaseCount())
                        .collect(Collectors.toList()))
                .orElseThrow(IllegalArgumentException::new);

        mostLovedCategories.sort(Comparator.comparing(Category::getName));
        return mostLovedCategories.stream().map(Category::getName).collect(Collectors.toList());
    }

    private void categorizePurchases(Map<String, List<String>> usersPurchases) {
        usersPurchases.values()
                .forEach(purchase -> categories
                        .forEach(category -> category.increaseCountIfPresent(purchase)));
    }

    private void verifyAllPurchases(Map<String, List<String>> usersPurchases) {
        List<String> allPurchases = usersPurchases.values().stream()
                .flatMap(Collection::stream)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        List<String> allProducts = categories.stream()
                .flatMap(category -> category.getProducts().stream())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        for (String purchase: allPurchases) {
            int count = 0;
            for (String product: allProducts)
                if (product.equalsIgnoreCase(purchase))
                    count++;

            if (count == 0) throw new IllegalArgumentException();
        }
    }

    static List<String> popularShoppingCategories(Map<String, List<String>> usersPurchases) {
        return new Shopping().popularCategories(usersPurchases);
    }
}
