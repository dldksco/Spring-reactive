## scheduler란?
- 쓰레드를 관리하는 쓰레드 관리자 역할
- 구독시점에 데이터가 emit되는 영역과 emit된 영역을 operating하는 영역을 나눠서 멀티쓰레딩을 쉽게함
- 크게 operator chain에서 전환하는 오퍼레이터와
- 스케줄러를 통해 실행되는 쓰레드 실행모델을 지정하는게 있음

## 전용 operator

- publishOn(): operator 체인에서 downstream operator의 실행을 위한 쓰레드를 지정
- subscribeOn(): 최상위 Upstream publisher의 실행을 위한 쓰레드를 지정 원본 데이터 소스를 emit하기 위하 ㄴ쓰레드를 정함
- parallel(): Downstream에 대한 데이터 처리를 병렬로 분할 처리하기 위한 쓰레드를 지정함

## ParellelFlux의 동작방식

- Flux.parallel() - 워크로드 -> 리액터 시퀀스상에서 처리해야할 워크로드는 퍼블리셔가 에밋한 데이터들임
- ParallelFlux.runOn(Scheduler) : 이걸 사용해 스케쥴러를 지정하면 이 시점에 병렬처리를 시작함
- Rail area라는 논리적인 곳에서 작업들을 처리하게됨

## publishOn()과 subscribeOn()의 동작 이해
- Operator 체인에서 최초 쓰레드는 subscribe()가 호추ㅜㄹ되는 scope에 있는 쓰레드이다.
- operator 체인에서 publishOn()이 호출되면 호출 이후의 체인 즉 downstream은 다음 pusblishon을 만나기전까지 publishOn에서 지정한 thread에 싫행됨

- subscribeon()은 메인쓰레드를 다른 쓰레드로 바꾸는역할을 

- 만약 subscribeOn()과 publishOn()이 같이 있다면, publishOn()을 만나기 전까지의 Upstream Opeartor 체인은 subscribeOn()에서 지정한
쓰레드에서 실행되고, publishOn()을 만날때마다 publishOn()아래의 operator체인 downstream은 publishOn()에서 지정한 쓰레드에서 실행됨

- publishon()아래쪽에 SubscribeOn이 위치했다면, subscribeon이 어디있든간에 알아서 실행 쓰레드를 변경함 


## 스케쥴러의 종류

- .immediate(): 별도의 쓰레드를 추가 할당하지 않고, 현재 쓰레드에서 실행함.
- .single(): 하나의 쓰레드를 재사용함
  - 저지연일회성 실행에 최적화됨
- boundedElastic() : 쓰레드풀을 생성해 생성된 쓰레드를 재사용함
  - 생성할 수 있는 쓰레드 수에 제한이 있다.(default, cpu코어수 x10)
  - 긴 실행 시간을 가질 수 있는 blocking I/O 작업에 최적화됨
  - 대량의 데이터를 받아들이고 내보낼때는 다른 작업에 지장이 주지 않기 위해 쓰레드를 할당해 사용함
- parallel() : 여러개의 쓰레드를 할당해 동시에 작업을 수행함
  - nonblocking 작업에 최적화됨(cpu코어만큼만)
- fromExecutorService()
  - 기존의 executorservice를 사용해 쓰레드를 생성함(딱히 권장하지않음)
  - 의미있는 식별자를 제공해 Metric에 주로 사용
- newXXXX() : 다양한 유형의 새로운 Scheduler를 생성할 수 있음