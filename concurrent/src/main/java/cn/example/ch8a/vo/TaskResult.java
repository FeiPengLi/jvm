package cn.example.ch8a.vo;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8a.vo
 * ClassName: TaskResult
 *
 * @author: 李朋飞
 * @time: 2022/1/16 下午 14:52
 **/
public class TaskResult<R> {
    private final TaskResultType resultType;//方法执行结果

    private final R returnVale;//方法执行后的结果数据

    private final String reson;//如果方法失败，可以填充原因

    public TaskResult(TaskResultType resultType, R returnVale, String reson) {
        this.resultType = resultType;
        this.returnVale = returnVale;
        this.reson = reson;
    }

    public TaskResult(TaskResultType resultType, R returnVale) {
        this.resultType = resultType;
        this.returnVale = returnVale;
        this.reson="Success";
    }

    public TaskResultType getResultType() {
        return resultType;
    }

    public R getReturnVale() {
        return returnVale;
    }

    public String getReson() {
        return reson;
    }

    @Override
    public String toString() {
        return "TaskResult{" +
                "resultType=" + resultType +
                ", returnVale=" + returnVale +
                ", reson='" + reson + '\'' +
                '}';
    }
}
