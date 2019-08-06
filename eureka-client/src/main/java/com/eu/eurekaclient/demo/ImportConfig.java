package com.eu.eurekaclient.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ItpscSelector.class)
public class ImportConfig {
}
