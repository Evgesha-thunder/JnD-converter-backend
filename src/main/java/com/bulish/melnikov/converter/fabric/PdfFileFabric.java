package com.bulish.melnikov.converter.fabric;


import com.bulish.melnikov.converter.convert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PdfFileFabric extends FileFabric {

    @Autowired
    public PdfFileFabric(List<PdfConverter> converters) {
        super("pdf");
        for (PdfConverter converter : converters) {
            this.converters.put(converter.getFormat(), converter);
        }
    }
}

