package com.siftscience.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {
    @Expose @SerializedName("$item_id") private String itemId;
    @Expose @SerializedName("$product_title") private String productTitle;
    @Expose @SerializedName("$price") private Long price;
    @Expose @SerializedName("$currency_code") private String currencyCode;
    @Expose @SerializedName("$exchange_rate") private ExchangeRate exchangeRate;
    @Expose @SerializedName("$quantity") private Long quantity;
    @Expose @SerializedName("$upc") private String upc;
    @Expose @SerializedName("$sku") private String sku;
    @Expose @SerializedName("$isbn") private String isbn;
    @Expose @SerializedName("$brand") private String brand;
    @Expose @SerializedName("$manufacturer") private String manufacturer;
    @Expose @SerializedName("$category") private String category;
    @Expose @SerializedName("$tags") private List<String> tags;
    @Expose @SerializedName("$color") private String color;
    @Expose @SerializedName("$size") private String size;

    public String getIsbn() {
        return isbn;
    }

    public Item setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getSize() {
        return size;
    }

    public Item setSize(String size) {
        this.size = size;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public Item setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public Item setProductTitle(String productTitle) {
        this.productTitle = productTitle;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public Item setPrice(Long price) {
        this.price = price;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public Item setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
        return this;
    }

    public Item setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public String getUpc() {
        return upc;
    }

    public Item setUpc(String upc) {
        this.upc = upc;
        return this;
    }

    public String getSku() {
        return sku;
    }

    public Item setSku(String sku) {
        this.sku = sku;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Item setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Item setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Item setCategory(String category) {
        this.category = category;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public Item setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Item setColor(String color) {
        this.color = color;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Item setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }
}
