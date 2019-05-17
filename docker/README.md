1. Build spring boot jar
    ```$xslt
    gradle clean build
    ```
1. Build docker image
    ```$xslt
    docker build -t xuujie/demo:latest .
    ```
2. Run container on port 8080
    ```$xslt
    docker run --name demo -p 8080:8080 xuujie/demo:latest
    ```
