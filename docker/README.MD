Start MongoDB:

// Seems to not work with macOS
docker run --name hellgile-mongo -v /Users/vadim/work/hellgile/docker/storage:/data/db -d mongo

// Storing data inside container
docker run --name hellgile-mongo -d -p 27017:27017 mongo