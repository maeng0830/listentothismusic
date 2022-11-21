package com.maeng0830.listentothismusic.util;

import java.util.HashMap;
import java.util.Map;
import org.springframework.data.domain.Page;

public class Paging {

    public static Map<String, Double> createPagingElement(Page<?> pageList) {

        Map<String, Double> pagingElement = new HashMap<>();

        double start = Math.floor(
            (pageList.getPageable().getPageNumber() / pageList.getPageable().getPageSize())
                * pageList.getPageable().getPageSize() + 1);
        double last = start + pageList.getPageable().getPageSize() - 1 < pageList.getTotalPages()
            ? start + pageList.getPageable().getPageSize() - 1 : pageList.getTotalPages();
        int pageNumber = pageList.getPageable().getPageNumber();
        int pageSize = pageList.getPageable().getPageSize();
        int totalPages = pageList.getTotalPages();

        pagingElement.put("start", start);
        pagingElement.put("last", last);
        pagingElement.put("pageNumber", (double) pageNumber);
        pagingElement.put("pageSize", (double) pageSize);
        pagingElement.put("totalPages", (double) totalPages);

        return pagingElement;
    }
}
