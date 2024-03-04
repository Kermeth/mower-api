## Mower API

This is a simple API to control a mower on a grassland grid.

### Prerequisites
* Docker
* Docker Compose

### Run

To run the API, you need to execute the following command:
```bash
docker-compose up
```

Then you can curl the API endpoint `/deploy` with the necessary instructions to control the mower:
```bash
curl -X POST -H "Content-Type: text/plain" --data "5 5                            
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM" http://localhost:8080/deploy
```
or use postman with the same payload.

### Instructions format
The instructions should be in the following format:
```
5 5 # (x,y) Grassland grid size
1 2 N # (x,y, N|E|S|W) Mower 1 initial position and orientation
LMLMLMLMM # (L|R|M) Mower 1 instructions
3 3 E # Mower 2 initial position and orientation
MMRMMRMRRM # Mower 2 instructions
```

### Swagger

The API has a swagger documentation available at `http://localhost:8080/swagger-ui.html`
There you can test the application as well.