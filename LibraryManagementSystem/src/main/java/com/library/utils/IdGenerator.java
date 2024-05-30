package com.library.utils;

public class IdGenerator {
    private static int bookId = 0;
    private static int memberId = 0;

    public static int generateBookId() {
        return ++bookId;
    }

    public static int generateMemberId() {
        return ++memberId;
    }

	public static int generateTransactionId() {
		
		return 0;
	}
}
