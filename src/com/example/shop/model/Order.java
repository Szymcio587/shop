package com.example.shop.model;

import java.util.List;
import java.util.Objects;

public record Order(String id, double value, List<String> promotions) {}
