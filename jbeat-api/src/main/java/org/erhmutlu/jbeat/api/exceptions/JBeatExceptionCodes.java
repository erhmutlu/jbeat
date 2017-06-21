package org.erhmutlu.jbeat.api.exceptions;

/**
 * Created by erhmutlu on 15/06/17.
 */
public class JBeatExceptionCodes {

    //  Validation
    public static final int NULL_PARAMETER = 52281000;
    public static final int EMPTY_PARAMETER = 52281001;
    public static final int WRONG_TYPE_PARAMETER = 52281002;

    //  Periodic Task
    public static final int PERIODIC_TASK_NOT_FOUND_BY_TASKNAME = 52282000;
    public static final int MULTIPLE_ACTIVE_PERIODIC_TASK_FOUND_BY_TASKNAME = 52282001;
    public static final int MULTIPLE_PERIODIC_TASK_FOUND = 52282002;
    public static final int PERIODIC_TASK_ALREADY_EXIST = 52282003;
    public static final int PERIODIC_TASK_NOT_FOUND_BY_TASKNAME_OR_QUEUE = 52282004;
    public static final int PERIODIC_TASK_NOT_FOUND_BY_ID = 52282005;


}
