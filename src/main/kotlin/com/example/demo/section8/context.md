## Reactor Sequence상에서 상태를 저장할 수 있고, 저장돤 상태 값을 operator 체인에서 공유해서 사용할 수 있는 인터페이스
- context 상태 저장 값은 key,value
- 값을 저장하기 위해서는 contextWirte() 사용
- 값을 읽어오기 위해서는 읽기 전용 뷰인 ContextView를 사용
- Reactor Sequence에서 deferContextual() 또는 transformDeferredContextual()를 통해 제공

## 자주사용되는 context api 
- put(key,value) : key/value형태로 context에 값을씀
- context.of(key1,value1....) key/value 형태로 context에 여러개의 값을씀
- putall(contextview): 파라미터로 입력된 contextview를 mnerge함
- delete(key): context에서 key에 해당하는 value를 삭제함

## 자주사용되는 contextview api
- get(key) : contextview에서 key에 해당하는 value를 반환함
- getorempty(key): contextview에서 key에 해당하는 value를 optional로 래핑해서 반환함
- getordefault(key, default value): context view에서 key에 해당하는 value를 가져옴 key에 해당하는 value가 없으면 default value를 가져옴
- hashkey(key): contextview에서 특정key가 존재하는지 확인
- isEmpty(): context가 비었는지 확인

## 컨텍스트의 특징
- context는 subscriber를 통해 reactor sequence에 연결되며 체인에서 각각의 operator들이 실행 쓰레드가 달라도 연결된 context에 접근할 수 있다
- context는 체인의 맨아래에서부터 위로 전파됨
  - context는 downstreamd에서 upstream으로 전파됨
  - operator체인에서 context read메서드가 context write 메서드 밑에 있는 경우에는 write된 값을 read할 수 없음
  - 따라서 일반적으로 context에 write할때에는 operator체인의 마지막에 둔다
- 동일한 키에 대해서 write할 경우, 값을 덮어씀
- 메인 operatror 내부에서 sequence를생성하는 flatmpa()같은 operator내에서 write된 context의 값은 inner sequence내부에서만 유효 외부 operator에서 보이지 않음
- 