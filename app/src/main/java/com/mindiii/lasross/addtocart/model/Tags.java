package com.mindiii.lasross.addtocart.model;

public class Tags {

    int term_id;
    String name;
    String slug;
    int term_group;
    int term_taxonomy_id;
    String taxonomy;
    String description;
    int parent;
    int count;
    String filter;

    public int getTerm_id() {
        return term_id;
    }

    public void setTerm_id(int term_id) {
        this.term_id = term_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getTerm_group() {
        return term_group;
    }

    public void setTerm_group(int term_group) {
        this.term_group = term_group;
    }

    public int getTerm_taxonomy_id() {
        return term_taxonomy_id;
    }

    public void setTerm_taxonomy_id(int term_taxonomy_id) {
        this.term_taxonomy_id = term_taxonomy_id;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
