# Cold Sequence

- Cold Publisher
- 콜드 퍼블리셔가 1,3,5,7이라는 데이터를 emit을함
- subscriber 1 이 1,3,5,7의 데이터가 차례대로 전달함
- 이때 subscriber2가 subcriber1이 3이라는 데이터를 받을 때,
- 구독을 해도 똑같이 1,3,5,7을 받음
- 즉 subscriber가 구독할 때마다 타임라인의 처음부터 emit된 모든 데이터를 받을 수 있음
- 구독할때마다 타임라인이 하나씩 생긴다고 보면 됨

# Hot Sequence

- Hot publisher
- 핫 퍼블리셔가 1,3,5,7이라는 데이터를 emit함
- subscriber1은 1,3,5,7을 다 받음
- 근데 subscriber2가 subscriber1이 1,3을 받을때 구독을 한다면
- 콜드 시퀀스와는 다르게 5,7만 받게됨

즉 subscriber가 구독한 시점의 타임라인부터 emit된 데이터를 받을 수 있음