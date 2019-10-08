# Journal Manuscript Preparation System  

A journal editorial board manages submission/publication process of journal articles.
Its members ask experts to read and review papers.
Each reviewer needs to be informed (e.g. by email) about a new paper assigned to him/her to review.
He/she downloads the paper, prepares a review and writes notes into a single large text area or uploads a self-prepared PDF file.
Editorial board, as well as authors want to know, which articles are already reviewed and which are under review.

  ___

## Basic functionality of the System

  * Store articles in the database
  * Enable to upload new article to database
  * Assign an article to an expert to review
  * Inform an expert by email about a new article being assigned to him/her
  * Enables to download an article
  * Enables to upload a review
  * Inform editorial board/authors about a new uploaded review
  * View list of articles, reviews based on roles
  * View list of articles based on state (new, in revision, reviewed, accepted, rejected)
  * Archive papers after 6 months from upload

## Users of the System
* Editors
* Authors
* Reviewers
* Admin

## Implementation ideas
  * Maven
  * Spring, SpringBoot
  * JPA2, Hibernate with PostgreSQL
  * Spring Security - authentication, authorization
  * Observer design pattern
  * REST
  * Spring email (MailChimp API)
  * ? Spring statemachine
