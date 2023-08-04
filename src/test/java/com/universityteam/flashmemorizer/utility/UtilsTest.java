package com.universityteam.flashmemorizer.utility;

import com.universityteam.flashmemorizer.dto.CardDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilsTest {

    @Test
    public void testGetElementsInAWithoutB_RemoveCardWithSameTermAndDesc() {
        // Arrange
        List<CardDTO> listA = Arrays.asList(
                CardDTO.builder().id(1L).term("Term 1").desc("Desc 1").build(),
                CardDTO.builder().id(2L).term("Term 2").desc("Desc 2").build(),
                CardDTO.builder().id(3L).term("Term 3").desc("Desc 3").build()
        );

        List<CardDTO> listB = Arrays.asList(
                CardDTO.builder().id(2L).term("Term 2").desc("Desc 2").build()
        );

        List<CardDTO> expected = Arrays.asList(
                CardDTO.builder().id(1L).term("Term 1").desc("Desc 1").build(),
                CardDTO.builder().id(3L).term("Term 3").desc("Desc 3").build()
        );

        // Act
        List<CardDTO> result = Utils.getElementsInAWithoutB(listA, listB);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testGetElementsInAWithoutB_EmptyLists() {
        // Arrange
        List<CardDTO> listA = Arrays.asList();
        List<CardDTO> listB = Arrays.asList();

        // Act
        List<CardDTO> result = Utils.getElementsInAWithoutB(listA, listB);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetElementsInAWithoutB_RemoveAllCards() {
        // Arrange
        List<CardDTO> listA = Arrays.asList(
                CardDTO.builder().id(1L).term("Term 1").desc("Desc 1").build(),
                CardDTO.builder().id(2L).term("Term 2").desc("Desc 2").build()
        );

        List<CardDTO> listB = Arrays.asList(
                CardDTO.builder().id(1L).term("Term 1").desc("Desc 1").build(),
                CardDTO.builder().id(2L).term("Term 2").desc("Desc 2").build()
        );

        // Act
        List<CardDTO> result = Utils.getElementsInAWithoutB(listA, listB);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void testHtmlToPlainText_SimpleHtml() {
        final String html = "<html><body><h1>Hello</h1><p>Testing Jsoup</p></body></html>";
        final String expected = "Hello Testing Jsoup ";

        String result = Utils.htmlToPlainText(html);

        assertEquals(expected, result);
    }

    @Test
    public void testHtmlToPlainText_MultipleParagraphs() {
        final String html = "<div><p>Paragraph 1</p><p>Paragraph 2</p></div>";
        final String expected = "Paragraph 1 Paragraph 2 ";

        String result = Utils.htmlToPlainText(html);

        assertEquals(expected, result);
    }
}