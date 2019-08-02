apiVersion: v1
kind: Service
metadata:
  name: {APP_NAME}-service
  namespace: uat
spec:
  selector:
    app: {APP_NAME}
  ports:
  - name: http
    port: 8089
    targetPort: 8089
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: {APP_NAME}-deployment
  namespace: uat
  labels:
    app: {APP_NAME}
spec:
  replicas: 1
  selector:
    matchLabels:
      app: {APP_NAME}
  template:
    metadata:
      labels:
        app: {APP_NAME}
    spec:
      containers:
      - name: {APP_NAME}
        image: {IMAGE_URL}:{IMAGE_TAG}
        ports:
        - containerPort: 8089
        env:
          - name: SPRING_PROFILES_ACTIVE
            value: {SPRING_PROFILE}
---
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  name: {APP_NAME}
  namespace: uat
  annotations: 
    kubernets.io/ingress.class: "traefik"
spec:
  rules:
  - host: dockerweb.k8s.local
    http:
      paths:
      - path: 
        backend:
          serviceName: {APP_NAME}-service
          servicePort: 8089
