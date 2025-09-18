# ServiceBot

Простий чат-бот для збору анонімних відгуків співробітників (механіків, електриків, менеджерів) з аналізом через OpenAI API та інтеграцією з Google Sheets

##  Запуск проєкту

### 1. Клонування репозиторію
```bash
git clone https://github.com/Victoria-069/serviceBot.git
cd serviceBot

### 2. Налаштування середовища
Створи файл src/main/resources/application.properties і додай:
server.port=8080
openai.api.key=ВАШ_OPENAI_API_KEY
# API ключ можна отримати на OpenAI

### 3. Збірка і запуск
./mvnw spring-boot:run

### 4. Використання API 
- Почати сесію
GET http://localhost:8080/chat/start?userId=123
- Обрати роль
GET http://localhost:8080/chat/message?userId=123&text=Механік
- Обрати філію
GET http://localhost:8080/chat/message?userId=123&text=Київ-Центр
- Надіслати відгук
GET http://localhost:8080/chat/feedback?userId=123&text={Закінчились інструменти}

### 5. Google Sheets інтеграція
Відгуки автоматично дублюються у таблицю ServiceBot через Google Apps Script


