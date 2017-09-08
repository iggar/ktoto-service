# KTOTO Service
A REST API for quiz game

## Configuration

The service uses the following environment variables:

`PORT`

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
