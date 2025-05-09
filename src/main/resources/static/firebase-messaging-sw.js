// Firebase 메시징 서비스 워커 파일
importScripts("https://www.gstatic.com/firebasejs/11.7.1/firebase-app-compat.js")
importScripts("https://www.gstatic.com/firebasejs/11.7.1/firebase-messaging-compat.js")

// 콘솔 로그 추가
console.log("[firebase-messaging-sw.js] 서비스 워커 로드됨")

// Initialize Firebase (fix for undeclared variable)
const firebase = self.firebase

// Firebase 초기화
firebase.initializeApp({
    apiKey: "**입력**",
    authDomain: "**입력**",
    projectId: "**입력**",
    storageBucket: "**입력**",
    messagingSenderId: "**입력**",
    appId: "**입력**",
    measurementId: "**입력**",
})

// Firebase 메시징 인스턴스 가져오기
const messaging = firebase.messaging()

// 백그라운드 메시지 핸들링
messaging.onBackgroundMessage((payload) => {
    console.log("[firebase-messaging-sw.js] 백그라운드 메시지 수신:", payload)

    const notificationTitle = payload.notification.title || "알림"
    const notificationOptions = {
        body: payload.notification.body || "",
        icon: "/favicon.ico",
    }

    self.registration.showNotification(notificationTitle, notificationOptions)
})

// 서비스 워커 설치 이벤트
self.addEventListener("install", (event) => {
    console.log("[firebase-messaging-sw.js] 서비스 워커 설치됨")
    self.skipWaiting()
})

// 서비스 워커 활성화 이벤트
self.addEventListener("activate", (event) => {
    console.log("[firebase-messaging-sw.js] 서비스 워커 활성화됨")
    return self.clients.claim()
})

console.log("[firebase-messaging-sw.js] 서비스 워커 스크립트 실행 완료")
