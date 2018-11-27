package com.epam.infohandling.service;

import com.epam.infohandling.entity.Component;
import com.epam.infohandling.entity.ComponentEnum;

import java.util.List;

public interface ComponentService {

     List<Component> getParagraph(Component text) ;

     List<Component> getSentences(Component text);

     List<Component> getWords(Component text);

}
