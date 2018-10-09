# Java App as gray box to solve typical run-time issues
Длительность 21 ак. час.

Тренинг для QA, работающих с функционирующими Java-приложениями, который поможет им быстрее и качественнее решать специфические для Java проблемы с run-time характеристиками: OOME, GC pauses, SOE, threading issues, deadlocks, concurrency issues.
Требуются знания и навыки в объеме тренинга «01. Java for QA. Intro to Java Platform and language»

Цель тренинга: обеспечение нефункциональных атрибутов качества собранного и работающего Java-приложения.

## Участники после курса смогут:
- локализовать и исправить своими силами ряд типовых runtime-ошибок приложения (NPE, IOE, SQLE)
- локализовать и исправить своими силами ряд типовых проблем с памятью (OOME, SOE, gc pauses)
- сформулировать ключевые граничные условия типового java app для создания тестов (сеть, IO, threads, heap, stack)
- профилировать и семплировать java app для выявления узких мест


## Java App Dev
- git
- maven
- java syntax 
- OOAD

## IO
- File IO
- Networking
- Intro NIO2
- CAP

## Threads: parallelism
- Thread API

## Data Race
- JMM

## JVM Architecture (3)
- Threads
- Stack
- Heap
- JVM options for heap and stack tuning
- JVisualVM demo
- Типовые граничные условия для тестов

## GC and Heap Architecture (3)
- Garbage
- Heap architecture
- Types of GC
- GC and Heap tuning
- JVM options for GC tuning
- GC console demo
- Типовые граничные условия для тестов

## Heap and Stack Monitoring (3)
- Sampling vs Profiling
- Heap and GC monitoring with JVisualVM demo
- Stack and threads monitoring with JVisualVM demo
- Heap and Stack Dumps with JVisualVM demo
- Heap and Stack Dumps with CLI tools demo
- Practice: Live App profiling

## Java App Troubleshooting (3)
- Main trouble reasons
- Logging frameworks
- Exceptions in logs
- JVM crash dumps
- GC collection pauses
- Типовые граничные условия для тестов

## Concurrency data issues (3)
- Java Memory Model
- Typical concurrency errors
- Data correctness issues

## Typical Enterprise App Architecture (3)
- Layers: UI, BL, DAL
- Frameworks overview (Spring, EJB)
- JDBC
- AppServer architecture
- ThreadPools
- ConnectionPools
- PreparedStatement Caches
- Типовые граничные условия enterprise java app для тестов

## Buffer (3)
