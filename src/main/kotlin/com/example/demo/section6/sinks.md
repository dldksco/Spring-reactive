# Sinks란?
- reactive streams에서 발생하는 signal을 프로그래밍적으로 push할 수 있는 publisher의 종류
- thread-safe 하지 않는 processor는 (퍼블리셔와 섭스크라이버의 특징을 동시에 가지고있음)보다 나은 대안을 가짐
  - onnext oncomplete onerror 메소드를 직접적으로 호출해서 문제가 됨
- sinks는 동시접근을 감지하고 동시접근하는 쓰레드중 하나를 빠르게 실패해서 보장할 수 있음
- Sinks.Many or Sinks.One interface를 사용해 thread-safe하게 signal을 발생시킴