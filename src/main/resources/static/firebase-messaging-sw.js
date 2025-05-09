// Firebase 메시징 서비스 워커 파일
// Firebase 앱 호환성 라이브러리와 Firebase 메시징 호환성 라이브러리를 가져옵니다.
importScripts("https://www.gstatic.com/firebasejs/11.7.1/firebase-app-compat.js")
importScripts("https://www.gstatic.com/firebasejs/11.7.1/firebase-messaging-compat.js")

// 콘솔 로그를 통해 서비스 워커가 로드되었음을 확인합니다.
console.log("[firebase-messaging-sw.js] 서비스 워커 로드됨")

// self.firebase를 통해 Firebase 인스턴스를 가져옵니다.
const firebase = self.firebase

// Firebase 앱을 초기화합니다. 실제 값으로 변경해야 합니다.
firebase.initializeApp({
    apiKey: "**입력**",
    authDomain: "**입력**",
    projectId: "**입력**",
    storageBucket: "**입력**",
    messagingSenderId: "**입력**",
    appId: "**입력**",
    measurementId: "**입력**",
})

// Firebase 메시징 인스턴스를 가져옵니다.
const messaging = firebase.messaging()

// 백그라운드에서 메시지를 수신할 때 실행되는 이벤트 핸들러
messaging.onBackgroundMessage((payload) => {
    console.log("[firebase-messaging-sw.js] 백그라운드 메시지 수신:", payload)

    // 알림 제목과 옵션을 설정합니다.
    const notificationTitle = payload.notification.title || "알림"
    const notificationOptions = {
        body: payload.notification.body || "",
        icon: "/images/warning.png",
    }

    // 알림을 표시합니다.
    self.registration.showNotification(notificationTitle, notificationOptions)
})

// 서비스 워커 설치 이벤트 핸들러
self.addEventListener("install", (event) => {
    console.log("[firebase-messaging-sw.js] 서비스 워커 설치됨")
    self.skipWaiting()
})

// 서비스 워커 활성화 이벤트 핸들러
self.addEventListener("activate", (event) => {
    console.log("[firebase-messaging-sw.js] 서비스 워커 활성화됨")
    return self.clients.claim()
})

console.log("[firebase-messaging-sw.js] 서비스 워커 스크립트 실행 완료")
