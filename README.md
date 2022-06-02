`docker build -t pokemon-app .` - build docker image
`docker run -dp 8080:8080 pokemon-app` - run pokemon app on 8080 port

`docker rmi -f $(docker images -f "dangling=true" -q)` - remove intermediate images
`docker kill $(docker ps -q)` - kill all docker containers