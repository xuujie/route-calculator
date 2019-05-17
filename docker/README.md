1. Build spring boot jar
    ```$xslt
    gradle clean build
    ```
2. Build docker image
    ```$xslt
    docker build -t xuujie/docker-demo:latest .
    ```
3. Run container on port 8080
    ```$xslt
    docker run --name docker-demo -p 8080:8080 xuujie/docker-demo:latest
    ```
4. Clean up
    ```$xslt
    docker container stop docker-demo
    docker container rm docker-demo
    docker image rm xuujie/docker-demo:latest
    ```
5. Push to docker hub
    ```$xslt
    docker login --username=xuujie --email=xuujie@gmail.com
    docker tag eb8f1854806e xuujie/docker-demo:latest
    docker push xuujie/docker-demo:latest
    ```