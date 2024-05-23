package cn.aicnn.chatssespringboot.controller;

import cn.aicnn.chatssespringboot.dto.AIAnswerDTO;
import cn.aicnn.chatssespringboot.service.GptServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;


/*****
 * 这个控制器类提供了两个接口，分别用于处理聊天响应和字符更新事件的请求，并使用 SSE 技术以流式方式返回结果给前端
*/
@RestController
public class ChatController {

    //用于流式请求第三方的实现类
    @Resource
    GptServiceImpl gptService;

    //通过stream返回流式数据
    @GetMapping(value = "/completions", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<AIAnswerDTO>> getStream(@RequestParam("messages")String messages) {
        return gptService.doChatGPTStream(messages)//实现类发送消息并获取返回结果
                .map(aiAnswerDTO -> ServerSentEvent.<AIAnswerDTO>builder()//进行结果的封装，再返回给前端
                        .data(aiAnswerDTO)
                        .build()
                )
                .onErrorResume(e -> Flux.empty());//发生异常时发送空对象
    }




    //用于测试事件 ServerSentEvent
    // ServerSentEvent（简称SSE）是一种Web技术，允许服务器向浏览器发送实时更新。
    // 在Java中，ServerSentEvent是一个用于表示单个服务器发送事件数据项的类，它通常包含一个或多个字段，如数据正文、事件类型、事件ID以及可选的重试间隔等信息。
    //Flux 类型在响应式编程中代表了一个0到N个元素的异步序列，它是Reactor库（以及其他响应式编程库，例如RxJava）中的一个重要概念。Flux的核心特性是它的异步非阻塞行为和流式处理能力，这使得它特别适用于处理源源不断的数据流或事件流。
    //当我们谈论持续推送时，关键在于Flux的设计允许数据的按需生成和消费。这意味着，只要数据可用或者满足特定条件（如时间间隔、事件触发等），Flux就能够源源不断地产生新的元素并通过订阅机制推送给订阅者。
    //对于传统的集合类型（如List、Set等），它们一次性存储所有元素，并且在创建后就不再改变大小或内容。这种类型不适合用于持续推送，因为它无法动态地添加新的元素，也不具备异步推送的能力。
    //本质区别：
    //动态生成 vs 静态存储：
    //Flux是动态生成的，可以在任意时刻添加新的数据元素。
    //其他集合类型则是静态存储的，一旦创建就确定了其元素内容。
    //异步推送 vs 同步访问：
    //Flux支持异步推送，数据生产者可以在任何时候将新数据推送到消费者那里，无需消费者主动请求。
    //静态集合类型则需要消费者主动请求（遍历、查询等）才能获取数据。
    //背压支持：
    //Flux等响应式类型支持背压（backpressure），即在数据生产速度大于消费速度时，能够控制数据流的速度，防止内存溢出等问题。
    //静态集合类型没有内置的背压机制。
    //所以，当我们要实现服务器持续推送数据到客户端时，使用像Flux这样的响应式序列类型是最合适的选择，因为它可以很好地模拟和管理这种持续流动的数据流。而在实际的应用场景中，比如与ServerSentEvent结合，Flux可以将一系列的数据转换为一系列的SSE事件，从而实现持续、有序且可控的推送过程。
    @GetMapping(value = "/character-events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<Character>> getCharacterEvents(@RequestParam("messages") String messages) {
        // 将输入字符串转换为Character对象的列表
        List<Character> characterList = messages.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());

        // 将Character对象列表转换为Flux<Character>
        Flux<Character> characterStream = Flux.fromIterable(characterList);

        // 方法1 对每个Character对象应用延迟
        return characterStream.delayElements(Duration.ofMillis(1000))
                .map(character -> {
                    // 创建并初始化ServerSentEvent
                    return ServerSentEvent.<Character>builder()
                            .data(character)
                            .id(Character.toString(character)) // 可选地，设置事件ID为当前字符的字符串形式
                            .event("character-update") // 设置事件类型
                            .build();
                })
                .onErrorResume(e -> Flux.empty());

//        // 方法2  对每个Character对象应用延迟
//        return Flux.interval(Duration.ofMillis(1000))
//                .zipWith(Flux.fromIterable(characterList), (index, character) -> character)
//                .map(character -> {
//                    // 创建并初始化ServerSentEvent
//                    return ServerSentEvent.<Character>builder()
//                            .data(character)
//                            .id(Character.toString(character)) // 可选地，设置事件ID为当前字符的字符串形式
//                            .event("character-update") // 设置事件类型
//                            .build();
//                })
//                .take(characterList.size()) // 确保只发送列表中的字符数量
//                .onErrorResume(e -> Flux.empty());


    }


}
