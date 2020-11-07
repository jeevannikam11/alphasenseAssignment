Feature: Alphasense assignment tests

  @Test
  Scenario Outline: Add new pet into the store
    Given Add pet to the store using addPet API
      | petId        | <petId>        |
      | categoryId   | <categoryId>   |
      | categoryName | <categoryName> |
      | petName      | <petName>      |
      | photoUrl     | <photoUrl>     |
      | tagId        | <tagId>        |
      | tagName      | <tagName>      |
      | status       | <status>       |
    Then pet is added with response code 200
    Examples:
      | petId | categoryId | categoryName | petName | photoUrl                        | tagId | tagName       | status    |
      | 1     | 12         | cat1         | myPet1  | http://url.com, http://url1.com | 13,14 | myTag1,myTag2 | available |

  @Test
  Scenario Outline: Delete pet from the store
    Given Add pet to the store using addPet API
      | petId        | <petId>        |
      | categoryId   | <categoryId>   |
      | categoryName | <categoryName> |
      | petName      | <petName>      |
      | photoUrl     | <photoUrl>     |
      | tagId        | <tagId>        |
      | tagName      | <tagName>      |
      | status       | <status>       |
    Then pet is added with response code 200
    Then Delete pet from the store with petId "<petId>"
    Then pet is deleted with response code 200
    Examples:
      | petId | categoryId | categoryName | petName | photoUrl                        | tagId | tagName       | status    |
      | 15    | 12         | cat1         | myPet1  | http://url.com, http://url1.com | 13,14 | myTag1,myTag2 | available |

  @Test
  Scenario Outline: Order pet from the store
    Given Add pet to the store using addPet API
      | petId        | <petId>        |
      | categoryId   | <categoryId>   |
      | categoryName | <categoryName> |
      | petName      | <petName>      |
      | photoUrl     | <photoUrl>     |
      | tagId        | <tagId>        |
      | tagName      | <tagName>      |
      | status       | <status>       |
    Then pet is added with response code 200
    Then Order a pet using order pet API
      | orderId  | <orderId>     |
      | petId    | <petId>       |
      | quantity | <quantity>    |
      | shipDate | <shipDate>    |
      | status   | <orderStatus> |
      | complete | <complete>    |
    Then check order pet status as "<orderStatus>"
    Examples:
      | petId | categoryId | categoryName | petName | photoUrl                        | tagId | tagName       | status    | orderStatus | orderId | quantity | shipDate                 | complete |
      | 21    | 12         | cat1         | myPet1  | http://url.com, http://url1.com | 13,14 | myTag1,myTag2 | available | placed      | 1       | 2        | 2020-11-07T14:43:02.354Z | true     |
