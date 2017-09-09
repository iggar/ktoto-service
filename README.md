# KTOTO Service
A REST API for quiz game

## Configuration

The service uses the following environment variables:

`PORT` - PORT number where the server will run (default: 11001).

`NUM_QUESTIONS` - number of questions in the game (default: 5).

`NUM_CHOICES` - number of choices per question (default: 3).


## Endpoints

### Starting a new Game

`POST /game/new`

### Answer a question


### Healthcheck
`GET /internal/healthcheck`

### Current Configuration
`GET /internal/config`


## Tests

Run:

`lein test`



##TODO

- [ ] Add Schema
- [ ] Add Rollbar
- [ ] Dockerise
- [ ] Add Makefile
