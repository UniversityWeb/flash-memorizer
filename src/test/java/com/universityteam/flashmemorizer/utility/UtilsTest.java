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
        final String html = "<p>This is <strong>bold</strong> text and <em>italic</em> text.</p>";
        final String expected = "This is bold text and italic text.";

        String result = Utils.htmlToPlainText(html);

        assertEquals(expected, result);
    }

    @Test
    public void testHtmlToPlainText_ListItems() {
        final String html = "<ul><li>Item 1</li><li>Item 2</li><li>Item 3</li></ul>";
        final String expected = "Item 1 Item 2 Item 3";

        String result = Utils.htmlToPlainText(html);

        assertEquals(expected, result);
    }

    @Test
    public void testHtmlToPlainText_Heading() {
        final String html = "<h1>Welcome to My Website</h1>";
        final String expected = "Welcome to My Website";

        String result = Utils.htmlToPlainText(html);

        assertEquals(expected, result);
    }
}