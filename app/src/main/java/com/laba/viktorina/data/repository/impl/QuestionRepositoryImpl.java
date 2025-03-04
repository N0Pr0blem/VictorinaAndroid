package com.laba.viktorina.data.repository.impl;

import com.laba.viktorina.data.model.DifficultyLevel;
import com.laba.viktorina.data.model.Question;
import com.laba.viktorina.data.repository.QuestionRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class QuestionRepositoryImpl implements QuestionRepository {

    private final List<Question> questions = Arrays.asList(
            new Question(1L, "Какого цвета небо в ясный день?", "Синий",
                    Arrays.asList("Зеленый", "Красный", "Желтый"),
                    DifficultyLevel.EASY, "Посмотри вверх в солнечный день!"),

            new Question(2L, "Сколько лап у кошки?", "Четыре",
                    Arrays.asList("Две", "Шесть", "Восемь"),
                    DifficultyLevel.EASY, "Они такие же, как у собаки."),

            new Question(3L, "Как зовут главного героя мультфильма 'Шрек'?", "Шрек",
                    Arrays.asList("Фиона", "Осёл", "Гарри"),
                    DifficultyLevel.EASY, "Он зелёный и живёт в болоте."),

            new Question(4L, "Какой металл является основным в производстве алюминия?", "Алюминий",
                    Arrays.asList("Железо", "Медь", "Золото"),
                    DifficultyLevel.EASY, "Этот металл лёгкий и используется в самолётах."),

            new Question(5L, "Сколько будет 2 + 2?", "4",
                    Arrays.asList("3", "5", "6"),
                    DifficultyLevel.EASY, "Это самое простое сложение!"),

            new Question(6L, "Как называется самый большой океан на Земле?", "Тихий",
                    Arrays.asList("Атлантический", "Индийский", "Северный Ледовитый"),
                    DifficultyLevel.EASY, "Этот океан называют 'мирным'."),

            new Question(7L, "Кто написал роман 'Война и мир'?", "Лев Толстой",
                    Arrays.asList("Фёдор Достоевский", "Александр Пушкин", "Николай Гоголь"),
                    DifficultyLevel.EASY, "Этот писатель носил длинную бороду."),

            new Question(8L, "Как называется столица Франции?", "Париж",
                    Arrays.asList("Лондон", "Берлин", "Рим"),
                    DifficultyLevel.EASY, "Здесь находится Эйфелева башня."),

            new Question(9L, "Как называется маленькая планета, которая вращается вокруг Солнца?", "Астероид",
                    Arrays.asList("Комета", "Спутник", "Звезда"),
                    DifficultyLevel.EASY, "Они часто находятся в 'поясе' между Марсом и Юпитером."),

            new Question(10L, "Как называется главный орган кровеносной системы?", "Сердце",
                    Arrays.asList("Печень", "Лёгкие", "Мозг"),
                    DifficultyLevel.EASY, "Этот орган бьётся внутри твоей груди."),

            new Question(11L, "Как называется самая высокая гора в мире?", "Эверест",
                    Arrays.asList("Килиманджаро", "Монблан", "Фудзияма"),
                    DifficultyLevel.EASY, "Она находится в Гималаях."),

            new Question(12L, "Как называется планета, на которой мы живём?", "Земля",
                    Arrays.asList("Марс", "Юпитер", "Венера"),
                    DifficultyLevel.EASY, "Её называют 'Голубая планета'."),

            new Question(13L, "Какой газ необходим человеку для дыхания?", "Кислород",
                    Arrays.asList("Углекислый газ", "Азот", "Водород"),
                    DifficultyLevel.EASY, "Он обозначается как O₂."),

            new Question(14L, "Сколько дней в неделе?", "7",
                    Arrays.asList("5", "6", "8"),
                    DifficultyLevel.EASY, "От понедельника до воскресенья."),

            new Question(15L, "Какой цвет получается при смешивании синего и жёлтого?", "Зелёный",
                    Arrays.asList("Красный", "Оранжевый", "Фиолетовый"),
                    DifficultyLevel.EASY, "Этот цвет есть у листьев летом.")
    );

    @Override
    public Optional<Question> findById(Long id) {
        return questions.stream()
                .filter(question -> question.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Question> findAllByDifficulty(DifficultyLevel difficulty) {
        return questions.stream()
                .filter(question -> question.getDifficulty().equals(difficulty))
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(Question question) {
        questions.add(question);
        return true;
    }

    @Override
    public boolean save(List<Question> questions) {
        this.questions.addAll(questions);
        return true;
    }

}
