# 2. Watcher Demo

Date: 2022-07-18

## Status

Accepted

## Context

Test watcher demo using zookeeper.

Create a `WatcherDemo` class and listen to various events.

Then open `zkCli.sh` and fire following commands to see it in action

- `create /target_znode "some data"` - Fires NodeCreated event
- `set /target_znode "add new data"` - Fires NodeDataChanged event
- `create /target_znode/child_1 "some data"` - Fires NodeChildrenChanged event
- `deleteall /target_znode` - Fires NodeDeleted event (1 for child node and 1 for main node)

## Decision

The change that we're proposing or have agreed to implement.

## Consequences

What becomes easier or more difficult to do and any risks introduced by the change that will need to be mitigated.
