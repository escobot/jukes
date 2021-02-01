# jukes

## Notes

Requirements:
- Java 11
- mvn
- Docker (optional)

Run using Docker:
```bash
# build image
docker build -t pbgnz/jukes .

# run image
docker run -p 8080:8080 -d pbgnz/jukes
```

API
```bash
# GET
/api/jukebox/{setting_id}?model={model}&offset={offset}&limit={limit}

# Example
curl localhost:8080/api/jukebox/2321763c-8e06-4a31-873d-0b5dac2436da?model=angelina&limit=2
```

