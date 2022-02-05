# my_travel_app

<h1>안드로이드 앱 만들때 참고 바람</h1>

<strong>and 11 ver 이상 부터 권한 설정이 바뀜</strong>
<p>1. 11월 1일부로 안드로이드 11 정책 변경
        2. 앞으로 QUERY_ALL_PACKAGES 권한을 사용하지 못함.
        3. QUERY_ALL_PACKAGES 대신 manifest에 &lt;queries&gt;를  추가하여 패키지를 설정해놓아야함.</p>
        
![권한 설정](https://user-images.githubusercontent.com/83574029/152647879-a2bf1d32-e7b5-4cf6-974f-d92ee7626def.PNG)
<br>
이런 식으로 설정 해줘야 됨
