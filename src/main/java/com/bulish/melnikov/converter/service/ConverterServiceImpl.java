package com.bulish.melnikov.converter.service;

import com.bulish.melnikov.converter.convert.Converter;
import com.bulish.melnikov.converter.fabric.ConverterFactory;
import com.bulish.melnikov.converter.fabric.FileFabric;
import com.bulish.melnikov.converter.model.ConvertRequest;
import com.bulish.melnikov.converter.model.State;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
@AllArgsConstructor
public class ConverterServiceImpl implements ConverterService {

    private final FileService fileService;
    private final ConverterFactory converterFactory;
    private final ConvertRequestService convertRequestService;
    @Override
    public void convert(ConvertRequest request) {
        request.setState(State.CONVERTING);
        convertRequestService.save(request);

        FileFabric fileFabric = converterFactory.getFactory(request.getFormatFrom());
        Converter converter = fileFabric.getConverter(request.getFormatTo());

        File fileToConvert = fileService.getFile(request.getFilePath());

        File convertedFile = null;
        try {
            convertedFile = converter.convert(fileToConvert);
            fileService.saveFile(convertedFile);
        } catch (IOException e) {
            request.setState(State.IN_ERROR);
            convertRequestService.save(request);
            throw new RuntimeException("Error converting file", e);
        }

        request.setConvertedFilePath(convertedFile.getAbsolutePath());
        request.setState(State.CONVERTED);
        convertRequestService.save(request);
    }
}
