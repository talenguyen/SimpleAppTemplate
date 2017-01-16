package vn.tale.architecture.pattern.list

/**
 * Created by Giang Nguyen on 1/14/17.
 */

interface Page {
  /**
   * Generate the next page
   * @return the next [Page]
   */
  operator fun next(): Page
}
