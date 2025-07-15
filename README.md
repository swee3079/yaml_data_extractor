# yaml_data_extractor

The project is developed to extract and read the data located within the YAML 
file under the resources directory and use them in further developments ahead.

>>>>>> application.yaml --------> This file contains the configurations related to the entire project. 
                                  For now it consists only the environment in which the application is 
                                  planned to be executed. 

                                  The ${ENVIRONMENT} mentioned in this YAML file ( dev / stag / prod ) 
                                  will determine which YAML file's data will be extracted to carry out
                                  the operation of this service. 

>>>>>> application-local.yaml---> Contains the configurations related to local environment testing
>>>>>> application-stag.yaml ---> Contains the configurations related to staging environment testing
>>>>>> application-prod.yaml ---> Contains the configurations related to prod environment testing



# Consists of 2 major sections
>> 1. Reading YAML file values using SnakeYAML
>> 2. Reading YAML file values Using Jackson Dataformat YAML