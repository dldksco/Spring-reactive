## Mono
- 0개 또는 1개의 데이터를 emit하는 publisher
- 데이터를 emit 과정에서 에러가 발생하면 onError signal을 emit함
- 제대로 종료했다면 onComplete signam을 emit함

## Flux
- 0 ~ N 개의 데이터를 emit하는 publisher이다
- 데이터 emit 과정에서 에러가 발생하면 onError signal을 emit함