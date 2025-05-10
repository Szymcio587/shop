package com.example.shop.model;

import java.util.Objects;

public record PaymentMethod(String id, double discount, double limit) {}
