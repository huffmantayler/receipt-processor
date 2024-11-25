# Fetch Receipt Processor

## To run

    - mvn clean package
    - docker build -t receipt-processor .
    - docker run -p 8080:8080 receipt processor

    Available Endpoints:
     - /receipts/process
     - /receipts/{id}/points
     