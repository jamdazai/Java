
// James Furaque  - 101382608
// Tenzin Thinley - 101454475
// Carl Trinidad  - 101425882

import java.lang.StringBuilder;
public class HugeInteger {
    private boolean isPositive;
    private Node head;
    private Node tail;
    private int length;

    // Constructors:
    // Public HugeInteger() && Public HugeInteger(String number)
    // An empty linked list is created. That is: isPositive must be set to true by default.
    // Head and tail are set to null.
    // If this variable is to be displayed,  a “0” should be printed (the zero is not stored).
    // Length must be set to 0;
    public HugeInteger() {
        this.isPositive = true;
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    // Creates the number from the string with all leading zeros (0) removed.
    public HugeInteger(String number){
        isPositive = true;                  // Create our Default Value
        head = null;
        tail = null;
        length = 0;

        // Check if the number is negative
        if (number.startsWith("-")) {
            isPositive = false;
            number = number.substring(1);
        }

        // We will remove the leading zeros then.
        int startIndex = 0;
        while (startIndex < number.length() && number.charAt(startIndex) == '0') {
            startIndex++;
        }

        // Now, let's create the linked list.
        for (int i = startIndex; i < number.length(); i++) {
            int digit = Character.getNumericValue(number.charAt(i));
            addDigit(digit);
        }
    }

    private void addDigit(int digit) {
        Node newNode = new Node(digit);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        length++;
    }


    // Additional Behaviours:
    // HugeInteger addPositive(HugeInteger num2)
    // Returns a new HugeInteger containing the result of adding num2 to the stored number.
    // You MUST assume num2 and the number being added to are BOTH positive.
    public HugeInteger addPositive(HugeInteger num2) {
        HugeInteger result = new HugeInteger();
        Node current1 = this.head;
        Node current2 = num2.head;
        int carry = 0;

        while (current1 != null || current2 != null || carry != 0) {
            int digit1 = (current1 != null) ? current1.digit : 0;
            int digit2 = (current2 != null) ? current2.digit : 0;

            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            int digit = sum % 10;

            result.addLast(digit);

            if (current1 != null) current1 = current1.next;
            if (current2 != null) current2 = current2.next;
        }

        return result;
    }

    // int compareTo (HugeInteger num2)
    // Returns -1 if the number stored is less than num2
    // Returns 0 if the number stored is equal to num2
    // Returns 1 if the number stored is greater than num2
    public int compareTo(HugeInteger num2) {
        if (this.length < num2.length) {
            return -1;                                          // Number stored is less than 2
        } else if (this.length > num2.length) {
            return 1;                                           // Number stored is greater than 2
        } else {
            Node current1 = this.head;
            Node current2 = num2.head;

            while (current1 != null) {
                if (current1.digit < current2.digit) {
                    return -1;
                } else if (current1.digit > current2.digit) {
                    return 1;
                }

                current1 = current1.next;
                current2 = current2.next;
            }

            return 0;                                           // Number stored is equal to num2
        }
    }

    // String toString()
    // This is where we could print the results to String
    // Returns a string representation of the number
    public String toString() {
        if (head == null){
            return "0";
        }
        StringBuilder result = new StringBuilder();
        Node current = head;

        while (current != null) {
            result.insert(0, current.digit); // Insert at the beginning to maintain the correct order
            current = current.next;
        }

        return result.toString();
    }

    // void concatenateDigit(int digit)
    // Adds a digit to the end of the number (at the front of the list).
    // Note: if the list is empty leading zeros should not be added.
    public void concatenateDigit(int digit) {
        if (head == null) {
            addDigit(digit);
        } else {
            Node newNode = new Node(digit);
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            length++;
        }
    }

    // void addLast(int digit)
    // Adds a digit to the front of the number (at the end of the list).
    // This can be used in the addPositive method
    public void addLast(int digit) {
        addDigit(digit);
    }
}
