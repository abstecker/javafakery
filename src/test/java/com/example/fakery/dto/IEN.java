package com.example.fakery.dto;

import net.datafaker.annotations.FakeForSchema;

@FakeForSchema("com.example.fakery.IENFakerTest#defaultSchema")
public class IEN {
    private String ien;

    public IEN(String ien) {
        this.ien = ien;
    }

    public String getIen() {
        return ien;
    }

    public String setIen(String ien) {
        return this.ien = ien;
    }

    @Override
    public String toString() {
        return ien;
    }
}
