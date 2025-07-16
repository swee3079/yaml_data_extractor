# yaml_data_extractor [OVERVIEW]

The project is developed to extract and read the data located within the YAML file under the resources 
directory and finding a better approach to use them further in the application's processes ahead.

>>>>>> application.yaml --------> This file contains the configurations related to the entire project. 
                                  For now it consists only the environment in which the application is 
                                  planned to be executed. 

                                  The ${ENVIRONMENT} mentioned in this YAML file ( dev / stag / prod ) 
                                  will determine which YAML file's data will be extracted to carry out
                                  the operation of this service. 

>>>>>> application-local.yaml---> Contains the configurations related to local environment testing
>>>>>> application-stag.yaml ---> Contains the configurations related to staging environment testing
>>>>>> application-prod.yaml ---> Contains the configurations related to prod environment testing



# Key points to consider
>> 1. Reading YAML file values using SnakeYAML
>> 2. Reading YAML file values Using Jackson Dataformat YAML
>> 3. An issue arose when trying to capture values in environment variables. So, as a solution the 
      YAML file was converted entirely to string to capture those values.
>> 3. Extracting the data into the MajorObj POJO class
>> 4. MajorObj POJO class can be used within the application to use the data that were extracted from the YAML files.