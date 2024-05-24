Feature: Add Application feature of GraphQL endpoint

  Application in the Document domain defines a usage domain with its own Documents.

  Scenario: Adding valid Application entities
    Given I have the following Application entitiy
      | HasKey        | No                |
      | OutputDataKey | ApplicationEntity |
    When I post it to the addNewApplication graphql endpoint
      | InputDataKey  | ApplicationEntity |
      | OutputDataKey | postToGraphQL     |
    Then the operation is executed successfully
      | InputDataKey   | postToGraphQL |
      | ExpectedResult | success       |