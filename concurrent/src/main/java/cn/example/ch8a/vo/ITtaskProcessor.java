package cn.example.ch8a.vo;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8a.vo
 * ClassName: ITtaskProcessor
 *
 * @author: 李朋飞
 * @time: 2022/1/16 下午 12:11
 *
 * 要求框架使用者实现的任务接口，因为任务的性质在调用时才知道，
 * 所以传入的参数和方法的返回值均使用泛型
 **/
public interface ITtaskProcessor<T,R> {

    TaskResult<R> taskExecute(T data);
}
