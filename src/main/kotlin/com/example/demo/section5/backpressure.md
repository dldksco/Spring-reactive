## Publisher와 Subscriber간의 프로세스
Subscriber : subscribe()
Publisher : Send onSubscribe signal (정상적으로 구독됐음을 알림)
Subscriber : request signal of N (데이터를 전송받기위해 보냄)
Publisher : Send onNext signal of N (전달받은 시그널에 해당하는 onNextSignal을 보냄)
Subscriber :request signal of N (퍼블리셔로부터 데이터 처리가 끝나면 다시 시그널전송)

onNext 반복하다가

퍼블리셔에서 데이터가 없다면 Send onComplete signal을 전송함
만약 도중에 에러가 발생한다면 onError signal을 전송하면서 프로세스가 종료됨

## Backpressure란?
- 들어오는 데이터를 적절하게 제어해서 과부하가 발생하지 않도록하는 수단
- Publisher에서 emit되는 데이터를 subscriber쪽에서 안정적으로 처리하기 위한 제어 기능
  - emit속도가 처리 속도보다 압도적으로 빨라서 데이터들이 쌓일수도 있음
- 요청 데이터의 개수를 제어하는 방법
  - subscriber가 적절히 처리할 수 있는 수준의 데이터 개수를 publisher에게 요청
- Backpressure 전략을 사용하는 방법
  - reactor에서 제공함
- Backpressure 전략
  - Ignore 전략
    - backpressure를 적용안함
  - ERROR 전략
    - DownStream으로 전달할 데이터가 버퍼에 가득 찰 경우, Exception을 발생시키는 전략
  - DROP 전략
    - DownStream으로 전달 데이터가 버퍼에 가득 찰 경우, 버퍼 밖에서 대기하는 먼저 emit 된 데이터부터 drop 시키는 전략
  - LATEST 전략
    - DownStream으로 전달할 데이터가 버퍼에 가득 찰 경우, 버퍼 밖에서 대기하는 가장 최근에(나중에) emit된 데이터부터 버퍼에 채우는 전략
  - Buffer전략
    - DownStream으 전달할 데이터가 버퍼에 가득 찰 경우, 버퍼 안에 있는 데이터를 drop
    - Buffer-DROP-LATEST 전략
      - 버퍼가 가득찼을 때 가장 최근에 들어온애를 drop
    - Buffer-DROP-OLDEST 전략
      - 가장 먼저 즉 가장 오래된 데이터를 drop