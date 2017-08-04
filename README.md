book-repositories
=========================
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/T5750/book-repositories/blob/master/LICENSE.md)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/T5750/book-repositories/pulls)
[![GitHub stars](https://img.shields.io/github/stars/T5750/book-repositories.svg?style=social&label=Stars)](https://github.com/T5750/book-repositories)
[![GitHub forks](https://img.shields.io/github/forks/T5750/book-repositories.svg?style=social&label=Fork)](https://github.com/T5750/book-repositories)

This repository contains commonly used Maven Archetype Templates.

Installation:
-------------
1. Check out the templates
2. From command line goto project root folder. For ex: C:/Apps/git/book-repositories/SpringCloudInAction
3. Execute the following maven commands to install maven archetype:

    SpringCloudInAction> mvn clean

    SpringCloudInAction> mvn archetype:create-from-project

    SpringCloudInAction> cd target/generated-sources/archetype

    SpringCloudInAction/target/generated-sources/archetype> mvn clean install

4. Repeat the same steps for all the archetype templates.
5. From Your IDE, while creating maven project filter the archetypes using 'com.sivalabs' and choose the template you want.
6. Enjoy :-)

### What's included

```
book-repositories/
├── v0.1
│   └── SpringBootInAction
├── v0.2
│   └── SpringInAction
└── v0.3
    └── SpringCloudInAction
```