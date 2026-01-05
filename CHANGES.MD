3.22.0 (2026-01-05)
=================
- Added support for `$user_email`, `$review.$reviewed_user_id` fields to `$create_content`, `$update_content` events
- Added support for `$brand_name`, `$site_country`, `$site_domain` fields to `$flag_content` event

3.21.2 (2025-07-29)
=================
- Bumped version for `gson` from `2.10` to `2.13.1`
- Bumped version for `okhttp` from `4.12.0` to `5.1.0`
- Bumped version for `okio` from `3.6.0` to `3.15.0`
- Bumped version for `commons-codec` from `1.15.0` to `1.19.0`

3.20.0 (2025-05-12)
=================
- Added support for `$promotions` complex field to `$update_account` event fields
- Added support for `$iata_carrier_code` to the `$segment` complex field
- Removed support for `$iata_carrier_code` in the `$booking` complex field

3.19.0 (2025-04-24)
=================
- Added support for `$exchange_rate` complex field to `$transaction`, `$create_order`,
  `$update_order` and `$wager` events, `$booking`, `$discount` and `$item` event fields 

3.18.0 (2025-03-28)
=================
- Added support for `$card_bin_metadata` complex field to `$payment_method`

3.17.1 (2024-12-31)
=================
- Included HTTP Status codes into `SiftException` message when possible
- Validate that `apiKey` and `okHttpClient` are not `null` when creating `SiftClient`
- Validate that `accountId` is not `null` when creating requests which require `accountId` 

3.17.0 (2024-11-15)
=================
- Added support for `$wager` event type in Events API
- Added support for `$minimum_deposit_amount`, `$maximum_deposit_amount`,
  `$minimum_withdrawal_amount`, `$maximum_withdrawal_amount`, `$current_balance`,
  and `$new_balance` fields to `$transaction` events
  
3.16.0 (2024-09-26)
=================
- Added support for `$iata_carrier_code` to the `$booking` complex field

3.15.2 (2024-07-11)
=================
- Added support for `$guest_user_id` and `$guest_booking_reference_id` fields to `$guest` complex field for `$create_order` and `$update_order` events

3.14.2 (2024-05-20)
=================
- Fixed bug with okHttp causing SocketTimeoutException

3.14.1 (2024-05-16)
=================
- Added support for warnings in Events API

3.13.1 (2024-03-28)
=================
- Upgrade OkHttp to [4.12.0](https://square.github.io/okhttp/changelogs/changelog_4x/#version-4120) and Okio to [3.6.0](https://square.github.io/okhttp/changelogs/changelog_4x/#version-4120 ) to fix transitive vulnerability [CVE-2023-3635](https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2023-3635)

3.13.0 (2023-09-19)
=================
- Add support for score percentiles in score API

3.12.0 (2023-09-01)
=================
- Add support for Verification API

3.11.0 (2023-06-29)
=================
- Add support for `$user_email` field to `$add_item_to_cart`, `$add_promotion`, `$content_status`, `$flag_content`, `$remove_item_from_cart` and `$update_password` events
- Add support for `$shipping_carrier` and `$shipping_tracking_numbers` fields to `$create_order` and `$update_order` events
- Add support for `$reason` field to `$flag_content` event

3.10.0 (2023-06-06)
=================
- Remove support for `$keyless_user_id` field for Events API 

3.9.0 (2023-03-10)
=================
- Add support for score percentiles

3.8.0 (2022-12-09)
=================
- Add support for `$ach_return_code` field to `$chargeback` event
- Add support for `$wallet_address` and `$wallet_type` fields to `$payment_method` complex field
- Add support for `$digital_order` complex field  
- Add support for `$digital_orders` field to `$transaction`, `$create_order`, and `$update_order` events
- Add support for `$receiver_wallet_address` and `$receiver_external_address` fields to `$transaction` event

3.7.0 (2022-11-09)
=================
- added support for merchant management api

3.6.1 (2022-08-18)
=================
- Added $verification_phone_number to $add_item_to_cart, $add_promotion, $content_status, $create_account, $create_content, $create_order, $custom_event, $flag_content, $login, $remove_item_from_cart, $transaction, $update_account, $update_content, $update_order, and $update_password events

3.5.2 (2022-07-27)
=================
- Added SiftClient constructor to override the baseUrl

3.5.1 (2022-07-01)
=================
- Added $keyless_user_id 
- Added support for $name and $phone is custom events


3.5.0 (2022-06-21)
=================
- Adding support for return_route_info query parameter for [Sync request](https://sift.com/developers/docs/curl/workflows-api/running-workflows/synchronous)

3.4.6 (2022-06-17)
=================
- Reverting Gradle version upgrade
- Adding support for $user_email in custom events


3.4.5 (2022-04-05)
=================
- Updated Gradle version to 6.8.3

3.4.4 (2022-04-04)
=================
- Add support for `$merchant_profile` complex field to `$create_account`, `$update_account`, and `$chargeback` events

3.4.3 (2021-10-12)
=================
- Add support for `$shortened_iban_first6`, `$shortened_iban_last4`, and `$sepa_direct_debit_mandate` to `$payment_method` complex field and `$transaction` event
- Add support for `$merchant_profile` complex field to `$create_order`, `$update_order`, and `$transaction` events
- Add support for `$status_3ds`, `$triggered_3ds`, `$merchant_initiated_transaction`, `$sent_address`, and `$received_address` to `$transaction` event

3.4.2 (2021-10-11)
=================
- Add support for `$account_number_last5`, `$account_holder_name`, `$bank_name` and `$bank_country` to `$payment_method` complex field

3.4.1 (2021-10-05)
=================
- Add support for `$user_email` field to `$login` event

3.4.0 (2020-05-05)
=================
- Add support for `$decline_category` field to `$transaction` event

3.3.0 (2020-02-19)
=================
- Add support for `$client_language` field to `$app` complex field
- Add support for `$accept_language` and `$content_language` fields to `$browser` complex field
- Add support for `$brand_name`, `$site_country` and `$site_domain` fields to custom events and all reserved events except `$chargeback`, `$link_session_to_user` and `$flag_content`
- Add support for `$ordered_from` complex field to `$create_order`, `$update_order`, and `$transaction` events

3.2.0 (2019-07-31)
=================
- Add support for `$tags` field to `$booking` complex field

3.1.0 (2019-07-17)
=================
- Add support for `$bookings` complex field to `$create_order` and `$update_order` events

3.0.0 (2019-05-14)
=================
- Add account id and OkHttpClient to constructor
- Add Circle build

2.7.0 (2019-02-18)
=================
- Add support for `$app` and `$browser` to `$content_status`, `$order_status`, and `$security_notification`

2.6.0 (2019-01-30)
=================

- Add support for `$account_types` to the `$create_account` event and the `$update_account` event
- Add support for the new event `$update_password`
- Add support for `$verified_event`, `$reason`, `$verified_entity_id` to the `$verification` event
- Add support for `$ip`, `$browser`, `$app` to the `$verification` event

2.5.0 (2019-01-09)
=================

- Add support for `$failure_reason` to the `$login` event
- Add support for `$account_types` to the `$login` event
- Add support for `$username` to the `$login` event
- Add support for `$social_sign_on_type` to the `$login` event

2.4.0 (2018-10-24)
=================
- Add support for Rescore User and Get User Score APIs
- Add support for `$app` and `$browser` to the following events: `AddItemToCart`, `AddPromotion`, `CreateAccount`, `CreateContent`, `CreateOrder`, `CustomEvent`, `Logout`, `RemoveItemFromCart`, `Transaction`, `UpdateAccount`, and `UpdateContent`
- Upgrade okhttp3 to 3.4.2

2.3.0 (2018-08-08)
=================

- Add support for Rescore User and Get User Score APIs

2.2.1 (2018-08-06)
=================

- Remove Guava dependency
- Replace use of internal Sun API class

2.2.0 (2018-07-05)
=================

- Add new query parameter force_workflow_run

2.1.0 (2018-06-04)
=================

- Adds support for session decisions to [Decisions API](https://siftscience.com/developers/docs/curl/decisions-api)

2.0.1 (2018-04-06)
=================

- Improved documentation in CHANGES.MD and README.md.
- Added missing fields to Message.java
- Removed deprecated files.

2.0 (2018-03-30)
=================

- Adds support for Sift Science API Version 205, including new [`$create_content`](https://siftscience.com/developers/docs/curl/events-api/reserved-events/create-content) and [`$update_content`](https://siftscience.com/developers/docs/curl/events-api/reserved-events/update-content) formats
- V205 APIs are now called -- **this is an incompatible change**
- Adds support for content decisions to [Decisions API](https://siftscience.com/developers/docs/curl/decisions-api)


INCOMPATIBLE CHANGES INTRODUCED IN API V205:
- `$create_content` and `$update_content` have significantly changed, and the old format will be rejected
- `$send_message` and `$submit_review` events are no longer valid
- V205 improves server-side event data validation. In V204 and earlier, server-side validation accepted some events that did not conform to the published APIs in our [developer documentation](https://siftscience.com/developers/docs/curl/events-api). V205 does not modify existing event APIs other than those mentioned above, but may reject invalid event data that were previously accepted. **Please test your integration on V205 in sandbox before using in production.**


1.5 (2018-02-09)
=================

- Add session level decisions in ApplyDecisionFieldSet.java.
- Add support for filtering get decisions by entity type SESSION.
- Add support for $security_notification event.

1.4.1 (2017-10-25)
=================

- Fix typo in ApplyDecisionFieldSet.java.
- Throw a ServerException for all 5xx server responses.

1.4 (2017-02-28)
=================

- Add support for $verification event.
- Add support for $app and $browser complex fields.

1.3.1 (2017-01-26)
==================

- Make base model classes public.
- Packaging with "jar" instead of "pom".

1.3 (2017-01-17)
==================

- Add support for decisions API.

1.2 (2016-12-14)
==================

- Initial public release.
