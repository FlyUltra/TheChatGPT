# TheChatGPT
![badge](https://img.shields.io/github/v/release/FlyUltra/TheChatGPT)
[![badge](https://jitpack.io/v/FlyUltra/TheChatGPT.svg)](https://jitpack.io/#FlyUltra/TheChatGPT)
![badge](https://img.shields.io/github/downloads/FlyUltra/TheChatGPT/total)
![badge](https://img.shields.io/github/last-commit/FlyUltra/TheChatGPT)
![badge](https://img.shields.io/badge/platform-spigot-lightgrey)
[![badge](https://img.shields.io/discord/896466173166747650?label=discord)](https://discord.gg/2PpdrfxhD4)
[![badge](https://img.shields.io/github/license/FlyUltra/TheChatGPT)](https://github.com/FlyUltra/TheChatGPT/blob/master/LICENSE.txt)

Very small API only about getting answer from ChatGPT from OpenAI!<br>
By using [**OpenAI-GPT3**](https://github.com/TheoKanning/openai-java)<br>
Only Spigot support!

## Table of contents

* [Getting started](#getting-started)
* [Depedencies](#dependencies)
* [Example](#example)
* [License](#license)

## Getting started

1. Add depedency into your maven/gradle
2. Create instance for ChatGPT
3. Use login, and register method
4. And have fun!

### Dependencies

Here you can see the configuration interface

<details>
    <summary>Maven</summary>

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.Fly_Ultra</groupId>
    <artifactId>TheChatGPT</artifactId>
    <version>VERSION</version>
    <scope>compile</scope>
</dependency>
```
</details>

<details>
    <summary>Gradle</summary>

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.Fly_Ultra:TheChatGPT:VERSION'
}
```
</details>

### Example

```java
    private ChatGPT chatGPT;
    
    @Override
    public void onEnable() {
        instance = this;
        
        // How to register ChatGPT
        chatGPT = new ChatGPT(this);
        chatGPT.login("your token", 0);
        chatGPT.register("AI", "You", "text-davinci-003", 0.9D, 50, 1.0D, 0D, 0.6D);
        
    }

    // Getter for ChatGPT
    public ChatGPT getChatGPT() {
        return chatGPT;
    }

    // How to get answer from AI
    getChatGPT().getAnswer(player, "How are you bro?");
```


## License
TheChatGPT is licensed under the permissive MIT license. Please see [`LICENSE.txt`](https://github.com/FlyUltra/TheChatGPT/blob/master/LICENSE.txt) for more information.