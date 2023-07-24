package com.universityteam.flashmemorizer.service;

import com.universityteam.flashmemorizer.dto.CardDTO;
import com.universityteam.flashmemorizer.service.impl.ImportCardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImportCardServiceTest {

    private ImportCardService importCardService;

    @BeforeEach
    public void setUp() {
        importCardService = new ImportCardServiceImpl();
    }

    static Stream<Arguments> testCasesExcelFileProvider() {
        return Stream.of(
                Arguments.of("Normal case",
                        "src/test/java/com/universityteam/flashmemorizer/service/normal-test.xlsx",
                        createCardsForNormalCase())
        );
    }

    private static List<CardDTO> createCardsForNormalCase() {
        return List.of(
                CardDTO.builder()
                        .term("Term 1")
                        .desc("Desc 1")
                        .build(),
                CardDTO.builder()
                        .term("Term 2")
                        .desc("Desc 2")
                        .build(),
                CardDTO.builder()
                        .term("Term 3")
                        .desc("Desc 3")
                        .build()
        );
    }

    @ParameterizedTest
    @MethodSource("testCasesExcelFileProvider")
    public void testImportCards_ExcelFile(
            String description,
            final String filePath,
            final List<CardDTO> expected
    ) {
        List<CardDTO> actual = importCardService.importCards(filePath);

        assertEquals(expected, actual, description);
    }

    static Stream<Arguments> testCasesFromUIProvider() {
        return Stream.of(
                Arguments.of(
                        "Normal case",
                        "increasing|Tang\n" +
                                "fall|Giam\n" +
                                "significant|Dang ke\n" +
                                "significant increase|Tang dang ke",
                        "|",
                        "\n",
                        createExpectedCardsList()
                ),
                Arguments.of(
                        "Empty content",
                        "",
                        "|",
                        "\n",
                        new ArrayList<>()
                )
        );
    }

    private static List<CardDTO> createExpectedCardsList() {
        List<CardDTO> cards = new ArrayList<>();
        cards.add(CardDTO.builder()
                .term("increasing")
                .desc("Tang")
                .build());
        cards.add(CardDTO.builder()
                .term("fall")
                .desc("Giam")
                .build());
        cards.add(CardDTO.builder()
                .term("significant")
                .desc("Dang ke")
                .build());
        cards.add(CardDTO.builder()
                .term("significant increase")
                .desc("Tang dang ke")
                .build());
        return cards;
    }

    @ParameterizedTest
    @MethodSource("testCasesFromUIProvider")
    public void testImportCards_FromUI(
            String description,
            final String content,
            final String betweenTermAndDesc,
            final String betweenCards,
            final List<CardDTO> expected
    ) {
        List<CardDTO> actual = importCardService.importCards(content, betweenTermAndDesc, betweenCards);

        assertEquals(expected, actual, description);
    }
}