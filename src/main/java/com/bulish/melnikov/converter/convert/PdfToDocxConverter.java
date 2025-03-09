package com.bulish.melnikov.converter.convert;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class PdfToDocxConverter extends PdfConverter {
    public PdfToDocxConverter() {
        super("docx");
    }

    @Override
    public byte[] convert(File file) {
        return null;
    }
}
