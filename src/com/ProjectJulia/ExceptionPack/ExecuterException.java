// =============================================================================
// Author(s): Giovanni Bamou Nana
// Course:    CS 4308 Section 2
// Instr:     Deepa Murallidar
// Project:   2nd Deliverable
// File:      ExecuterException.java
// =============================================================================
// Description:
// This file is the implementation of the ExecuterException class.
// =============================================================================

package com.ProjectJulia.ExceptionPack;


/**
 *
 * @author Giovanni Bamou Nana #000855399
 */

public class ExecuterException extends RuntimeException{

	 /**
     * Creates a new instance of <code>ExecuterException</code> without detail
     * message.
     */
    public ExecuterException() {
    }

    /**
     * Constructs an instance of <code>ExecuterException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExecuterException(String msg) {
        super(msg);
    }
}
