/**
 * Copyright 2013 Unicon (R) Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.apereo.oaa.services;

import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import weka.classifiers.Classifier;
import weka.classifiers.pmml.consumer.PMMLClassifier;
import weka.core.pmml.PMMLFactory;
import weka.core.pmml.PMMLModel;

/**
 * Handles the merging of the data inputs and processing in kettle with the selected PMML
 * 
 * @author Aaron Zeckoski (azeckoski @ unicon.net) (azeckoski @ vt.edu)
 */
@Component
public class ProcessorService {

    private static final Logger logger = LoggerFactory.getLogger(ProcessorService.class);

    @Autowired
    PreProcessorService preProcessorService;

    Classifier classifier;

    @PostConstruct
    public void init() {
        logger.info("INIT started");
        // TODO load up config
        // TOOD init weka PMML
        try {
            InputStream pmml = ProcessorService.class.getClassLoader().getResourceAsStream("pmml/oaai.marist.pmml.xml");
            // TODO - allow pmml file path override / config
            PMMLModel model = PMMLFactory.getPMMLModel(pmml);
            logger.info("Loaded PMML: "+model);
            if (model instanceof PMMLClassifier) {
                classifier = (PMMLClassifier) model;

                /* Since PMMLClassifier is a subclass of weka.classifiers.Classifier,
                 * you can use it just like any other Weka Classifier. The only
                 * exception is that calling buildClassifier() will raise an
                 * Exception because PMML models are pre-built.
                 */
             }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load pmml file and init the classifier: "+e, e);
        }

        // TODO init kettle
        logger.info("INIT completed");
    }

    @PreDestroy
    public void destroy() {
        logger.info("DESTROY");
    }

    public void process() {
        logger.info("PROCESS");
        // TODO execute the processor
    }

}
